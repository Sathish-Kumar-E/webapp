Implemented an endpoint /healthz that will do the following when called:

1) Check if the application has connectivity to the database.
   a) Return 'HTTP 200 OK' if the connection is successful.
   b) Return 'HTTP 503 Service Unavailable' if the connection is unsuccessful.

2) The API response will not be cached.

3) The API request will not allow for any payload. The response code will be '400 Bad Request' if the request includes any payload.

4) The API response will not include any payload.

5) Only HTTP GET method will be supported for the /healthz endpoint.
