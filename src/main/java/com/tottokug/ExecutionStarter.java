package com.tottokug;

import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflow;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflowClient;
import com.tottokug.workflow.SampleWorkflowClientExternal;
import com.tottokug.workflow.SampleWorkflowClientExternalFactory;
import com.tottokug.workflow.SampleWorkflowClientExternalFactoryImpl;

public class ExecutionStarter {

  public static void main(String[] args) {
    String domain = "sample-maven";
    if (args.length > 0) {
      domain = args[0];
    }

    AmazonSimpleWorkflow service = new AmazonSimpleWorkflowClient(new AWSCredentialsProviderChain(new InstanceProfileCredentialsProvider(), new ClasspathPropertiesFileCredentialsProvider()));

    SampleWorkflowClientExternalFactory factory = new SampleWorkflowClientExternalFactoryImpl(service, domain);
    SampleWorkflowClientExternal sampleWorkflowClientExternal = factory.getClient();
    sampleWorkflowClientExternal.workflow();
  }
}
