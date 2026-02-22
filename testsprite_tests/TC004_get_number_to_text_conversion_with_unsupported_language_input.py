import requests

def test_get_number_to_text_unsupported_language_fallback_to_french():
    base_url = "http://localhost:8081/T1.0/amounts"
    unsupported_language = "XX"
    numeric_value = "45"
    url = f"{base_url}/{unsupported_language}/value/{numeric_value}"
    headers = {
        "Accept": "application/json"
    }
    try:
        response = requests.get(url, headers=headers, timeout=30)
        response.raise_for_status()
    except requests.RequestException as e:
        assert False, f"Request failed: {e}"

    assert response.status_code == 200
    try:
        json_data = response.json()
    except ValueError:
        assert False, "Response is not valid JSON."

    assert "message" in json_data, "Response JSON missing 'message' key."
    assert "langue" in json_data, "Response JSON missing 'langue' key."
    assert "input" in json_data, "Response JSON missing 'input' key."

    # The language should fallback to French ("FR")
    assert json_data["langue"] == "FR", f"Expected fallback langue 'FR', got '{json_data['langue']}'"
    # The input should be the numeric string sent
    assert json_data["input"] == numeric_value, f"Expected input '{numeric_value}', got '{json_data['input']}'"
    # The message should be the French textual representation for 45: "Quarante-cinq"
    # Case-insensitive check with ignoring accents might be necessary but basic check:
    assert "quarante" in json_data["message"].lower(), f"Expected French textual representation in message, got '{json_data['message']}'"

test_get_number_to_text_unsupported_language_fallback_to_french()