package com.mall.order.payment.pojo;
/*************************************************************
 * 类：订单信息表
 * @author Lihj
 *************************************************************
 */
import java.util.Date;

public class PropertyPayment {
    public String pkBill;
    public String billNo;
    public String areaName;
    public String areaCode;
    public String userCode;
    public String roomCode;
    public Date beginDate;
    public Date endDate;
    public Double totalMoney;
    public char orderStatus;
    public char orderTime;
    public Date billDate;
    public char billType;
    public String statusCode;
    public String statusName;
    public Date createdTime;
    public Date serverTime;

    public String getPkBill() {
        return pkBill;
    }

    public void setPkBill(String pkBill) {
        this.pkBill = pkBill;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public char getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(char orderStatus) {
        this.orderStatus = orderStatus;
    }

    public char getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(char orderTime) {
        this.orderTime = orderTime;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public char getBillType() {
        return billType;
    }

    public void setBillType(char billType) {
        this.billType = billType;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getServerTime() {
        return serverTime;
    }

    public void setServerTime(Date serverTime) {
        this.serverTime = serverTime;
    }

}
