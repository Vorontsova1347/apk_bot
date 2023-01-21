import requests
import json

s = "https://83e3-188-235-32-210.ngrok.io/"
t = "5940213784:AAHZ5iqTCqkpup_7uPe94dPPRmnmmPfPKeE"
url = f"https://api.telegram.org/bot{t}/setWebhook"

payload = json.dumps({
    "url": s
})
headers = {
    'Content-Type': 'application/json'
}
response = requests.request("POST", url, headers=headers, data=payload)
print(response.text)
