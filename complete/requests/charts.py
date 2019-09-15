import requests
import auth

chart_url = "https://api.eu0.signalfx.com/v2/chart"

def create_chart(metric):
    chart = {
      "description": "Requests/s",
      "name": metric,
      "programText": "A = data('" + metric + "').publish(label='A')",
      "tags": ["observability_demo"]
    }

    chart_response = requests.post(chart_url, headers=auth.get_headers(), json=chart).json()
    print(chart_response)
    return chart_response['id']
