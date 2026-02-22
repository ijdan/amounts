import requests

def test_get_number_to_text_conversion_with_non_numeric_input():
    base_url = "http://localhost:8081/T1.0/amounts"
    langue = "EN"
    non_numeric_value = "12A3"
    url = f"{base_url}/{langue}/value/{non_numeric_value}"
    headers = {
        "Accept": "application/json"
    }
    timeout = 30

    try:
        response = requests.get(url, headers=headers, timeout=timeout)
        assert response.status_code == 400, f"Expected status code 400 but got {response.status_code}"
        json_response = response.json()
        assert "error" in json_response, "Response JSON should contain 'error' key"
        assert json_response["error"] == "Invalid numeric value", f"Expected error message 'Invalid numeric value' but got '{json_response['error']}'"
        assert "input" in json_response, "Response JSON should contain 'input' key"
        assert json_response["input"] == non_numeric_value, f"Expected input value '{non_numeric_value}' but got '{json_response['input']}'"
    except requests.RequestException as e:
        assert False, f"Request failed: {e}"

test_get_number_to_text_conversion_with_non_numeric_input()