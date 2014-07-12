package com.tottokug.workflow;

import com.tottokug.activity.SampleActivitiesClient;
import com.tottokug.activity.SampleActivitiesClientImpl;

public class SampleWorkflowImpl implements SampleWorkflow {

  SampleActivitiesClient sampleActivitiesClient = new SampleActivitiesClientImpl();

  @Override
  public void workflow() {
    System.out.println("workflow called");
    sampleActivitiesClient.activity();
  }

}
