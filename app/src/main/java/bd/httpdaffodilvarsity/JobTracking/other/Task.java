package bd.httpdaffodilvarsity.JobTracking.other;

import java.io.Serializable;

/**
 * Created by Kazi on 2/25/2017.
 */

public class Task implements Serializable{

    private String taskTitle;
    private String taskDescription;
    private String taskStartDate;
    private String taskEstDate;
    private String taskEndDate;
    private String taskStatus;
    private String percentDone;
    private String taskPriority;
    private String taskOwnerComment;
    private String taskInchargeComment;
    private String taskOwnerId;
    private String jobId;
    private String taskAccessibility;
    private String preOwnerId;
    private String taskCreatedBy;
    private String taskCreatedTime;
    private String taskUpdatedBy;
    private String taskUpdatedTime;

    public Task() {
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskStartDate() {
        return taskStartDate;
    }

    public void setTaskStartDate(String taskStartDate) {
        this.taskStartDate = taskStartDate;
    }

    public String getTaskEstDate() {
        return taskEstDate;
    }

    public void setTaskEstDate(String taskEstDate) {
        this.taskEstDate = taskEstDate;
    }

    public String getTaskEndDate() {
        return taskEndDate;
    }

    public void setTaskEndDate(String taskEndDate) {
        this.taskEndDate = taskEndDate;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getPercentDone() {
        return percentDone;
    }

    public void setPercentDone(String percentDone) {
        this.percentDone = percentDone;
    }

    public String getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(String taskPriority) {
        this.taskPriority = taskPriority;
    }

    public String getTaskOwnerComment() {
        return taskOwnerComment;
    }

    public void setTaskOwnerComment(String taskOwnerComment) {
        this.taskOwnerComment = taskOwnerComment;
    }

    public String getTaskInchargeComment() {
        return taskInchargeComment;
    }

    public void setTaskInchargeComment(String taskInchargeComment) {
        this.taskInchargeComment = taskInchargeComment;
    }

    public String getTaskOwnerId() {
        return taskOwnerId;
    }

    public void setTaskOwnerId(String taskOwnerId) {
        this.taskOwnerId = taskOwnerId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getTaskAccessibility() {
        return taskAccessibility;
    }

    public void setTaskAccessibility(String taskAccessibility) {
        this.taskAccessibility = taskAccessibility;
    }

    public String getPreOwnerId() {
        return preOwnerId;
    }

    public void setPreOwnerId(String preOwnerId) {
        this.preOwnerId = preOwnerId;
    }

    public String getTaskCreatedBy() {
        return taskCreatedBy;
    }

    public void setTaskCreatedBy(String taskCreatedBy) {
        this.taskCreatedBy = taskCreatedBy;
    }

    public String getTaskCreatedTime() {
        return taskCreatedTime;
    }

    public void setTaskCreatedTime(String taskCreatedTime) {
        this.taskCreatedTime = taskCreatedTime;
    }

    public String getTaskUpdatedBy() {
        return taskUpdatedBy;
    }

    public void setTaskUpdatedBy(String taskUpdatedBy) {
        this.taskUpdatedBy = taskUpdatedBy;
    }

    public String getTaskUpdatedTime() {
        return taskUpdatedTime;
    }

    public void setTaskUpdatedTime(String taskUpdatedTime) {
        this.taskUpdatedTime = taskUpdatedTime;
    }
}
