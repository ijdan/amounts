import requests

BASE_URL = "http://localhost:8081/T1.0/amounts"
TIMEOUT = 30

def test_get_number_to_text_conversion_valid_english_input():
    value = 123
    url = f"{BASE_URL}/EN/value/{value}"
    try:
        response = requests.get(url, timeout=TIMEOUT)
        response.raise_for_status()
    except requests.RequestException as e:
        assert False, f"Request failed: {e}"

    assert response.status_code == 200, f"Expected status code 200, got {response.status_code}"
    json_data = response.json()
    expected_message = "One hundred and twenty-three"
    assert isinstance(json_data, dict), "Response is not a JSON object"
    actual_message = json_data.get("message")
    assert actual_message is not None, f"Expected message field but got None"
    assert actual_message.lower() == expected_message.lower(), f"Expected message '{expected_message}', got '{actual_message}'"
    assert json_data.get("langue") == "EN", f"Expected language 'EN', got '{json_data.get('langue')}'"
    assert json_data.get("input") == str(value), f"Expected input '{value}', got '{json_data.get('input')}'"

test_get_number_to_text_conversion_valid_english_input()