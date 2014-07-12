package com.tottokug.host;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflow;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflowClient;
import com.amazonaws.services.simpleworkflow.flow.WorkerBase;

public abstract class HostAbstract extends Thread {
  AmazonSimpleWorkflow swfService;
  WorkerBase worker;
  boolean test;
  boolean halt_;
  String domain;
  static final String TEST_TASKLIST = "JUNIT";

  String taskList = null;

  public HostAbstract(String domain) {
    this.domain = domain;
    this.test = false;
    this.init();
  }

  public HostAbstract(String domain, boolean test) {
    this.domain = domain;
    this.test = test;
    this.init();
  }

  protected void init() {
    this.swfService = new AmazonSimpleWorkflowClient(new AWSCredentialsProviderChain(new InstanceProfileCredentialsProvider(), new ClasspathPropertiesFileCredentialsProvider()));
    this.activeTaskList = new ArrayList<String>();
    this.halt_ = false;
    if (this.test) {
      this.taskList = TEST_TASKLIST;
    }
  }

  abstract protected WorkerBase getWorker();

  public void halt() {
    this.halt_ = true;
    interrupt();
  }

  public abstract void destroy();

  protected abstract List<String> getActiveTaskLists();

  List<String> activeTaskList;

  public void run() {
    this.worker = getWorker();
    this.worker.start();
    while (true) {
      try {
        sleep(1000);
      } catch (InterruptedException e) {
      }
      if (this.halt_()) {
        break;
      }
    }
  }

  protected void updateActiveTasklist() {

  }

  static enum TaskListStatus {
    STOP, START, STAY
  }

  protected Map<TaskListStatus, String> tasklistCompare(List<String> newer, List<String> older) {
    Map<TaskListStatus, String> tasklistStatuses = new HashMap<HostAbstract.TaskListStatus, String>();
    return tasklistStatuses;
  }

  protected boolean halt_() {
    if (this.halt_) {
      try {
        // this.worker.getService().shutdown();
        // LongPollingのために30秒待ち
        this.worker.shutdownAndAwaitTermination(30000, TimeUnit.MILLISECONDS);
      } catch (InterruptedException e) {
        this.worker.shutdown();
      }
      return true;
    }
    return false;
  }

}