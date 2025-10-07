import requests

# URL of the Flask endpoint
url = "http://127.0.0.1:5000/message"

# Message to send
text_to_send = "Hello Flask!"

# Send POST request with JSON
response = requests.post(url, json={"text": text_to_send})

# Print server response
if response.ok:
    data = response.json()
    print("Server replied:", data["reply"])
else:
    print("Error:", response.status_code, response.text)
