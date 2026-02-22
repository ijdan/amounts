import requests

def test_get_number_to_text_conversion_valid_french_input():
    base_url = "http://localhost:8081/T1.0/amounts"
    langue = "FR"
    value = "123"
    url = f"{base_url}/{langue}/value/{value}"
    headers = {
        "Accept": "application/json"
    }
    try:
        response = requests.get(url, headers=headers, timeout=30)
        assert response.status_code == 200, f"Expected status code 200 but got {response.status_code}"
        json_response = response.json()
        assert "message" in json_response, "Response JSON missing 'message' key"
        assert "langue" in json_response, "Response JSON missing 'langue' key"
        assert "input" in json_response, "Response JSON missing 'input' key"
        assert json_response["message"].lower() == "cent vingt-trois", f"Expected message 'cent vingt-trois' but got '{json_response['message']}'"
        assert json_response["langue"] == langue, f"Expected langue '{langue}' but got '{json_response['langue']}'"
        assert json_response["input"] == value, f"Expected input '{value}' but got '{json_response['input']}'"
    except requests.RequestException as e:
        assert False, f"Request failed: {e}"

test_get_number_to_text_conversion_valid_french_input()