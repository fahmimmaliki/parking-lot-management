document.addEventListener('DOMContentLoaded', function() {
    fetchAvailablePlaces();

    // Function to fetch available places
    function fetchAvailablePlaces() {
        fetch('/api/parking/places')
            .then(response => response.json())
            .then(data => {
                document.getElementById('available-places').innerText = data.availablePlaces;
            });
    }

    // Park a car
    window.parkCar = function() {
        const licensee = document.getElementById('licensee').value;
        fetch('/api/parking/park', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ licensee })
        })
        .then(response => response.text())
        .then(message => {
            alert(message);
            fetchAvailablePlaces();
        });
    }

    // Leave a car
    window.leaveCar = function() {
        const carId = document.getElementById('carId').value;
        fetch(`/api/parking/leave/${carId}`, { method: 'POST' })
            .then(response => response.text())
            .then(message => {
                alert(message);
                fetchAvailablePlaces();
            });
    }
});
