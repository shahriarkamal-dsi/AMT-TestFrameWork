from apscheduler.schedulers.blocking import BlockingScheduler
import time
import datetime
import os

sched = BlockingScheduler()


@sched.scheduled_job('interval', minutes=20)
def timed_job():
    print("Logging in again..." + str(datetime.datetime.now()))
    f = open('ScheduleLog.txt', "a")
    if os.path.getsize("ScheduleLog.txt") > 0:
        f.write("\n" + (str(datetime.datetime.now())) + "  Test Started \n")
    else:
        f.write((str(datetime.datetime.now())) + "  Test Started \n ")

    os.system("mvn clean")
    os.system("mvn test -Dtest=TestCaseCreate test")
    f.close()

os.system("mvn clean")
os.system("mvn test -Dtest=TestCaseCreate test")

sched.start()
