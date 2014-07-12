package com.tottokug.host;

import java.util.List;

import com.amazonaws.services.simpleworkflow.flow.ActivityWorker;
import com.amazonaws.services.simpleworkflow.flow.WorkerBase;
import com.tottokug.activity.SampleActivitiesImpl;

public class ActivityHost extends HostAbstract {

  public ActivityHost(String domain, boolean b) {
    super(domain, b);
  }

  public ActivityHost(String domain) {
    super(domain);
  }

  public static void main(String[] args) {
    String domain = "sample-maven";
    if (args.length > 0) {
      domain = args[0];
    }
    ActivityHost host = new ActivityHost(domain);
    host.start();
  }

  @Override
  protected WorkerBase getWorker() {
    if (this.worker == null) {
      if (this.taskList == null) {
        this.taskList = "default";
      }
      this.worker = new ActivityWorker(this.swfService, this.domain, this.taskList);
      Object[] types = { new SampleActivitiesImpl() };
      for (Object type : types) {
        try {
          ((ActivityWorker) this.worker).addActivitiesImplementation(type);
        } catch (SecurityException e) {
          e.printStackTrace();
        } catch (InstantiationException e) {
          e.printStackTrace();
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        } catch (NoSuchMethodException e) {
          e.printStackTrace();
        }
      }
    }
    return this.worker;
  }

  @Override
  public void destroy() {
  }

  @Override
  protected List<String> getActiveTaskLists() {

    return null;
  }
}
