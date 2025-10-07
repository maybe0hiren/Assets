import React, { useState } from "react";

function MessageClient() {
  const [input, setInput] = useState("");
  const [reply, setReply] = useState("");

  const sendMessage = async () => {
    try {
      const response = await fetch("http://127.0.0.1:5000/message", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ text: input }),
      });

      const data = await response.json();
      setReply(data.reply);
    } catch (error) {
      console.error("Error:", error);
      setReply("Error connecting to server.");
    }
  };

  return (
    <div style={{ fontFamily: "sans-serif", margin: "40px" }}>
      <h2>Flask Message Client</h2>
      <input
        type="text"
        placeholder="Type something..."
        value={input}
        onChange={(e) => setInput(e.target.value)}
        style={{ padding: "8px", width: "300px", marginRight: "10px" }}
      />
      <button onClick={sendMessage} style={{ padding: "8px 12px" }}>
        Send
      </button>

      {reply && (
        <p style={{ marginTop: "20px", fontWeight: "bold" }}>Server: {reply}</p>
      )}
    </div>
  );
}

export default MessageClient;
