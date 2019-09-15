import charts
import dashboard_group

metrics = [
    "http.server.requests.count",
    "http.server.requests.totalTime",
    "jvm.memory.used",
    "jvm.threads.peak",
    "jvm.gc.memory.allocated",
    "tomcat.threads.current"
]

charts_list = []
width = 6
for num, name in enumerate(metrics, start=0):
    chartId = charts.create_chart(name)
    chartObject = {
        "chartId": chartId,
        "column": width * (num % 2),
        "row": num//2,
        "height": 1,
        "width": width
    }
    charts_list.append(chartObject)

response = dashboard_group.create_hierarchy(charts_list)
print(response)