import requests

def test_get_number_to_text_conversion_valid_spanish_input():
    base_url = "http://localhost:8081/T1.0/amounts/"
    langue = "ES"
    value = 5000
    url = f"{base_url}{langue}/value/{value}"
    headers = {
        "Accept": "application/json"
    }
    timeout = 30

    response = None
    try:
        response = requests.get(url, headers=headers, timeout=timeout)
        assert response.status_code == 200, f"Expected status code 200, got {response.status_code}"
        json_data = response.json()
        assert "message" in json_data, "Response JSON missing 'message' field"
        assert json_data.get("langue") == langue, f"Expected langue '{langue}', got '{json_data.get('langue')}'"
        assert json_data.get("input") == str(value), f"Expected input '{value}', got '{json_data.get('input')}'"
        # Validate expected Spanish textual representation for 5000 is "Cinco mil"
        expected_message = "Cinco mil"
        assert json_data["message"] == expected_message, f"Expected message '{expected_message}', got '{json_data['message']}'"
    except requests.RequestException as e:
        assert False, f"Request failed: {e}"

test_get_number_to_text_conversion_valid_spanish_input()
