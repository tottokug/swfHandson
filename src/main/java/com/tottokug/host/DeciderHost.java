package com.tottokug.host;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.simpleworkflow.flow.WorkerBase;
import com.amazonaws.services.simpleworkflow.flow.WorkflowWorker;
import com.tottokug.workflow.SampleWorkflowImpl;

public class DeciderHost extends HostAbstract {
  public static void main(String[] args) {
    String domain = "sample-maven";
    if (args.length > 0) {
      domain = args[0];
    }
    DeciderHost host = new DeciderHost(domain);
    host.start();
  }

  public DeciderHost(String domain) {
    super(domain);
  }

  public DeciderHost(String domain, boolean test) {
    super(domain, test);
  }

  @Override
  protected WorkerBase getWorker() {
    if (this.worker == null) {
      if (this.taskList == null) {
        this.taskList = "default";
      }
      WorkflowWorker w = new WorkflowWorker(this.swfService, this.domain, this.taskList);
      this.worker = w;
      try {
        w.addWorkflowImplementationType(SampleWorkflowImpl.class);
      } catch (InstantiationException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }

    }
    return this.worker;
  }

  @Override
  public void destroy() {

  }

  @Override
  protected List<String> getActiveTaskLists() {
    List<String> tasklists = new ArrayList<String>();
    return tasklists;
  }
}
