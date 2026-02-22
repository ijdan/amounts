import requests

def test_get_number_to_text_internal_server_error():
    base_url = "http://localhost:8081//T1.0/amounts"
    langue = "EN"
    value = "123"  # Using a valid numeric input to trigger internal server error scenario defined in PRD

    url = f"{base_url}/{langue}/value/{value}"
    try:
        response = requests.get(url, timeout=30)
    except requests.RequestException as e:
        assert False, f"HTTP request failed: {e}"

    assert response.status_code == 500, f"Expected status code 500 but got {response.status_code}"
    try:
        json_response = response.json()
    except ValueError:
        assert False, "Response is not valid JSON"

    assert "error" in json_response, "Response JSON does not contain 'error' key"
    assert json_response["error"] == "Internal server error", f"Expected error message 'Internal server error' but got '{json_response.get('error')}'"

test_get_number_to_text_internal_server_error()