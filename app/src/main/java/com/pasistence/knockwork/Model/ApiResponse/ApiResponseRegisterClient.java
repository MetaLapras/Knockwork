package com.pasistence.knockwork.Model.ApiResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ApiResponseRegisterClient {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("client")
    @Expose
    private List<Client> client = null;

    @SerializedName("message")
    @Expose
    private String message;

    @Override
    public String toString() {
        return "ApiResponseRegisterClient{" +
                "error=" + error +
                ", client=" + client +
                ", message='" + message + '\'' +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<Client> getClient() {
        return client;
    }

    public void setClient(List<Client> client) {
        this.client = client;
    }

    public class Client {

        @SerializedName("client_id")
        @Expose
        private String clientId;
        @SerializedName("uid")
        @Expose
        private String uid;
        @SerializedName("client_name")
        @Expose
        private String clientName;
        @SerializedName("client_email")
        @Expose
        private String clientEmail;
        @SerializedName("client_phone_no")
        @Expose
        private String clientPhoneNo;
        @SerializedName("client_image")
        @Expose
        private String clientImage;
        @SerializedName("client_provider")
        @Expose
        private String clientProvider;

        @Override
        public String toString() {
            return "Client{" +
                    "clientId='" + clientId + '\'' +
                    ", uid='" + uid + '\'' +
                    ", clientName='" + clientName + '\'' +
                    ", clientEmail='" + clientEmail + '\'' +
                    ", clientPhoneNo='" + clientPhoneNo + '\'' +
                    ", clientImage='" + clientImage + '\'' +
                    ", clientProvider='" + clientProvider + '\'' +
                    '}';
        }

        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getClientName() {
            return clientName;
        }

        public void setClientName(String clientName) {
            this.clientName = clientName;
        }

        public String getClientEmail() {
            return clientEmail;
        }

        public void setClientEmail(String clientEmail) {
            this.clientEmail = clientEmail;
        }

        public String getClientPhoneNo() {
            return clientPhoneNo;
        }

        public void setClientPhoneNo(String clientPhoneNo) {
            this.clientPhoneNo = clientPhoneNo;
        }

        public String getClientImage() {
            return clientImage;
        }

        public void setClientImage(String clientImage) {
            this.clientImage = clientImage;
        }

        public String getClientProvider() {
            return clientProvider;
        }

        public void setClientProvider(String clientProvider) {
            this.clientProvider = clientProvider;
        }

    }
}