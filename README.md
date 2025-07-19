
- Để xem đặc tả của API có thể sử dụng swagger
    + Truy cập trên trình duyệt tới địa chỉ: http://localhost:8080/swagger-ui/index.html


- Validate String fields định dạng date hoặc dateTime sử dụng annotation tự custom @ValidDateFormat
  + Ví dụ:  @ValidDateFormat(pattern = "dd/MM/yyyy HH:mm:ss")
            private String dateTime;
  + Trong đó pattern truyền động 1 định dạng date theo nhu cầu của Dev

