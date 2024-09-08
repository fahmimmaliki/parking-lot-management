# Parking Lot Management System

=============================

A simple web application for managing a parking lot.

## Getting Started

------

### Clone the Repository

```
git clone https://github.com/fahmimmaliki/parking-lot-management.git
```



### Install Dependencies

```
mvn install
```



### Run the Application using Docker Compose

```
docker compose up
```



This will start the application and make it available at `http://localhost:8080/api/parking`.

### Access the Application

```
http://localhost:8080/api/parking
```



### Endpoints

------

### Get Available Places

```
GET /api/parking/places
```



JSON Body Example:

```json
{}
```



Response:

```json
{
  "availablePlaces": 10
}
```



### Park a Car

```
POST /api/parking/park
```



JSON Body Example:

```json
{
  "licensee": "ABC123"
}
```



Response:

```json
{
  "message": "Car parked successfully"
}
```



### Leave a Car

```
POST /api/parking/leave/{id}
```



JSON Body Example:

```json
{}
```



Response:

```json
{
  "message": "Car has left. Bill: 5000"
}
```



### Get All Cars

```
GET /api/parking/cars
```



JSON Body Example:

```json
{}
```



Response:

```json
[
  {
    "id": 1,
    "licensee": "ABC123",
    "arrival": "2023-02-20T14:30:00",
    "leave": "2023-02-20T15:30:00",
    "parked": true,
    "bill": 5000
  },
  {
    "id": 2,
    "licensee": "DEF456",
    "arrival": "2023-02-20T15:30:00",
    "leave": "2023-02-20T16:30:00",
    "parked": true,
    "bill": 5000
  }
]
```



### Update Settings

```
PATCH /api/parking/settings
```



JSON Body Example:

```json
{
  "totalPlaces": 15,
  "payPerHour": 4000,
  "payFirstHour": 6000
}
```



Response:

```json
{
  "message": "Settings updated successfully"
}
```



### Get History

```
GET /api/parking/history
```



JSON Body Example:

```json
{}
```



Response:

```json
[
  {
    "licensee": "ABC123",
    "arrival": "2023-02-20T14:30:00",
    "leave": "2023-02-20T15:30:00",
    "bill": 5000
  },
  {
    "licensee": "DEF456",
    "arrival": "2023-02-20T15:30:00",
    "leave": "2023-02-20T16:30:00",
    "bill": 5000
  }
]
```



I hope this helps! Let me know if you have any questions or need further assistance.