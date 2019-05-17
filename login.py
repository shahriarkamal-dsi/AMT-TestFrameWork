import schedule
import time
import datetime
import os


def job():
    print("Logging in again..." + str(datetime.datetime.now()))
    f = open('ScheduleLog.txt', "a")
    if os.path.getsize("ScheduleLog.txt") > 0:
        f.write("\n"+(str(datetime.datetime.now())) + "  Test Started \n")
    else:
        f.write((str(datetime.datetime.now())) + "  Test Started \n ")
    os.system("mvn test -Dtest=TestCaseCreate test")
    f.close()


schedule.every(30).minutes.do(job)

while True:
    schedule.run_pending()
    time.sleep(1)
