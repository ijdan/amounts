import requests
import random
import sys
import time
from num2words import num2words
import re

BASE_URL = "http://localhost:8081/T1.0/amounts"
LANGUAGES = ["FR", "EN", "ES"]
ITERATIONS = 60

def generate_random_number():
    return random.randint(0, 999999999)

from unidecode import unidecode

def normalize_text(text, lang):
    """Normalize text for comparison by removing formatting differences."""
    if not text:
        return ""
    text = unidecode(text).lower()
    text = text.replace("-", " ").replace(",", "")
    
    # Pre-processing (remove conjunctions)
    if lang == "EN":
        text = text.replace(" and ", " ")
    elif lang == "FR":
        text = text.replace(" et ", " ")
    elif lang == "ES":
        text = text.replace(" y ", " ")
        
    # Strip all spaces
    text = text.replace(" ", "")
    
    # Post-processing (regex on compact string)
    if lang == "ES":
        # num2words quirk: "veintiunomil" -> "veintiunmil"
        text = re.sub(r'uno(mil|mill)', r'un\1', text)
        
    return text

def get_expected_text(number, lang):
    """Get expected text from num2words library."""
    try:
        if lang == "FR":
            return num2words(number, lang='fr')
        elif lang == "EN":
            return num2words(number, lang='en')
        elif lang == "ES":
            return num2words(number, lang='es')
    except Exception as e:
        print(f"Error generating expected text for {number} in {lang}: {e}")
        return None

def test_api():
    print(f"Starting API load test with {ITERATIONS} iterations (Range: 0-999,999,999)...")
    print("Using 'num2words' library for linguistic verification.")
    success_count = 0
    failure_count = 0

    for i in range(1, ITERATIONS + 1):
        number = generate_random_number()
        lang = random.choice(LANGUAGES)
        url = f"{BASE_URL}/{lang}/value/{number}"
        
        try:
            response = requests.get(url)
            if response.status_code == 200:
                data = response.json()
                
                api_input_number = data.get("inputNumber")
                api_text_result = data.get("response")
                api_language = data.get("language")
                
                # Basic Check: Input number matches
                if str(api_input_number) != str(number):
                    print(f"[{i}/{ITERATIONS}] FAILURE: {lang} {number} -> Number Mismatch. Got {api_input_number}")
                    failure_count += 1
                    continue

                # Check Language match
                if api_language != lang:
                    print(f"[{i}/{ITERATIONS}] FAILURE: {lang} {number} -> Language Mismatch. Got {api_language}")
                    failure_count += 1
                    continue

                # Linguistic Verification
                expected_text = get_expected_text(number, lang)
                
                norm_api = normalize_text(api_text_result, lang)
                norm_expected = normalize_text(expected_text, lang)
                
                if norm_api == norm_expected:
                    print(f"[{i}/{ITERATIONS}] SUCCESS: {lang} {number}")
                    success_count += 1
                else:
                    print(f"[{i}/{ITERATIONS}] FAILURE: {lang} {number}")
                    print(f"   API:      {api_text_result} (Norm: {norm_api})")
                    print(f"   Expected: {expected_text}   (Norm: {norm_expected})")
                    failure_count += 1
            else:
                print(f"[{i}/{ITERATIONS}] ERROR: Status {response.status_code} for {url}")
                failure_count += 1
                
        except Exception as e:
            print(f"[{i}/{ITERATIONS}] EXCEPTION: {e}")
            failure_count += 1
            
        time.sleep(0.05) 

    print("-" * 30)
    print(f"Test Completed.")
    print(f"Total Requests: {ITERATIONS}")
    print(f"Success: {success_count}")
    print(f"Failures: {failure_count}")
    
    if failure_count > 0:
        sys.exit(1)

if __name__ == "__main__":
    time.sleep(2)
    test_api()
