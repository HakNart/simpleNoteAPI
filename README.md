# Simple Note Spring Rest Api App

Demo Rest API project developed with Spring Boot framework and secured with spring security

## Running the app
Two ways to have run the app
### Method 1: Using docker
If you have docker set up, run the following command:
```
docker-compse up
```
Everything would be set up. The app can be accessed through
`http://localhost:8080`
### Method 2: Run on device
**Note**:Must have POSTGRES-SQL database on the computer the app will be run on
1. Set up `.env` file as shown in the `.env.example`
2. Run command `./mvnw spring-boot:run`

The app can be accessed through `http://localhost:8080`

## Security
The rest api application is secured with Spring Security as Resource server. It also serves partly as Authorization server 
by authenticating user's credential and grant jwt token for subsequent requests for which the app also verifies.
### To sign up for account
```bash
curl --location 'http://localhost:8080/api/auth/register' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": [insert email],
    "password": [insert password],
    "username": [insert username]
}'
```
### To login 
Your own account
```bash
curl --location 'http://localhost:8080/api/auth/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": [insert email],
    "password": [insert password]
}'
```
Or you can use the already set up account
```bash
curl --location 'http://localhost:8080/api/auth/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "user@email.com",
    "password": "user123"
}'
```
Successfully authenticated log in attempt will return the response as in this example:
```json
{
  "accessToken": "eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoidXNlckBlbWFpbC5jb20iLCJleHAiOjE2ODYxNDEzNjgsImlhdCI6MTY4NjEwNTM2OCwic2NvcGUiOiJST0xFX1VTRVIifQ.Vm1FOxflFJ8z6YGmonRogNdPmxzr0kCnJqUzqSTnhSyW_W48RtO_y6_6lWgypd_CC2cqdATudgLbsKqnGN38P4ho9dEK-menfCqlUYuuVMZamN3Ohkl1pm5lO8AsgQ2tJze65Ja80ZGdGXYimgSKjoSh_oXxCO4JOVbuanDBkIUDvQmZAREb1h34h70zeEtU0FEe1G0VOt0PF3h1_noNMLvgdv9bNBul2Vu3x3gVVqY2KCeKCt9akLw8WEeV0VKM4OgDaPSck_tMvmAEG6S8w8q-QFxa702G9m5JaVlpwqhWXMlZLux5wKYD2dRsoxYNK6AlQxRQpTJ4n2mZQgUEog",
  "email": "user@email.com"
}
```
The generated jwt token `accessToken` needs to be added to the `Authorization` header along with `Bearer ` for any other requests to be authenticated. For example:
```bash
curl --location 'http://localhost:8080/users/notes' \
--header 'Authorization: Bearer eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoidXNlckBlbWFpbC5jb20iLCJleHAiOjE2ODUzMTUyMjcsImlhdCI6MTY4NTI3OTIyNywic2NvcGUiOiJST0xFX1VTRVIifQ.P0xFYoXtuxMjVSUhryiZhhmq86vZr-nBuf8EX9jjJGQ8QbJ3aOq4uft5NIowAVJ608jTWhjInAVQbty2ZNaJr1puByY3HTUWjPsUpUR6C9627ESgi8brGVFRFF_uxscJjuTPIUGeeg-H9u9bwxtK8YHEzkPp3ZKLQogVoqCe46y5I4a986zV1VzUnWIlQOyYwZXUjTjV2ta95BggLfm4AJvfnn7DwiiB6vQr_GwyvQOXoK002RR4XQBMvHkvJnkzDHMIjBtljfS4zNFwuo9STOH64xr8DvHl0iih_iLk_UxwqfXW6btKuwer5XeaI4DlaINiTnUSO3Vo2bkwAUs9dg'
```
