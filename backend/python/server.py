from flask import Flask, request, jsonify
from flask_cors import CORS

app = Flask(__name__)
CORS(app)

@app.route('/message', methods=['POST'])
def handle_message():
    data = request.get_json()  # Receive JSON from client
    user_text = data.get("text", "")

    # Example processing
    reply = f"Server received: '{user_text}'"

    # Send back response
    return jsonify({"reply": reply})

if __name__ == '__main__':
    # Run on localhost:5000
    app.run(host='0.0.0.0', port=5000)
