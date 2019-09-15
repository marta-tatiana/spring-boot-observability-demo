import requests
import auth

group_url = "https://api.eu0.signalfx.com/v2/dashboardgroup"
dashboard_url = "https://api.eu0.signalfx.com/v2/dashboard"


def create_hierarchy(charts):
    data = create_group()
    return update_default_dashboard(charts, data['groupId'], data['dashboardId'])


def update_default_dashboard(charts, groupId, dashboardId):
    dashboard = {
        "description": "JDD",
        "name": "Observability demo",
        "charts": charts,
        "groupId": groupId
    }

    result = requests.put(dashboard_url + "/" + dashboardId, headers=auth.get_headers(), json=dashboard).json()
    print(result)
    return result['id']


def create_group():
    group = {
        "description": "Dashboard group for JDD presentation",
        "name": "Observability demo",
    }

    result = requests.post(group_url, headers=auth.get_headers(), json=group).json()
    return {"groupId": result['id'], "dashboardId": result['dashboardConfigs'][0]['dashboardId']}
