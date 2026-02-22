import requests

def test_get_number_to_text_out_of_range():
    base_url = "http://localhost:8081//T1.0/amounts/"
    langue = "EN"
    large_value = "999999999999999999999"
    url = f"{base_url}{langue}/value/{large_value}"
    headers = {
        "Accept": "application/json"
    }

    try:
        response = requests.get(url, headers=headers, timeout=30)
    except requests.RequestException as e:
        assert False, f"Request failed: {e}"

    assert response.status_code == 400, f"Expected status code 400 but got {response.status_code}"
    
    try:
        json_resp = response.json()
    except ValueError:
        assert False, "Response is not a valid JSON"

    assert "error" in json_resp, "Response JSON should contain 'error' key"
    assert json_resp["error"] == "Value out of supported range", f"Expected error message 'Value out of supported range' but got {json_resp.get('error')}"
    assert "input" in json_resp, "Response JSON should contain 'input' key"
    assert json_resp["input"] == large_value, f"Expected input value '{large_value}' in response but got {json_resp.get('input')}"

test_get_number_to_text_out_of_range()