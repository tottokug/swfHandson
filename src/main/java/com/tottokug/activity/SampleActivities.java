package com.tottokug.activity;

import com.amazonaws.services.simpleworkflow.flow.annotations.Activities;
import com.amazonaws.services.simpleworkflow.flow.annotations.Activity;
import com.amazonaws.services.simpleworkflow.flow.annotations.ActivityRegistrationOptions;

@Activities(activityNamePrefix="sample")
@ActivityRegistrationOptions(defaultTaskScheduleToStartTimeoutSeconds = 100, defaultTaskStartToCloseTimeoutSeconds = 100)
public interface SampleActivities {

  @Activity(version = "1.0-SNAPSHOT",name="activity")
  public void activity();

}
