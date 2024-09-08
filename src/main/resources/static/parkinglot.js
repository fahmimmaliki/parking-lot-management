$(document).ready(function() {
  // Fetch available parking places on page load
  updatePlacesCount();

  // Park a car
  $('#carButton').click(function() {
    const registrationNo = $('#carValue').val().trim();
    if (registrationNo) {
      $.ajax({
        url: 'http://localhost:8080/api/parking/park',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({ licensee: registrationNo }),  // API requires "licensee" key
        success: function(response) {
          $('#carValue').val('');  // Clear the input field
          addCarToTable(response);  // Add the new car to the table
          updatePlacesCount();      // Update available places count
        },
        error: function() {
          $('#message').removeClass('d-none').text('Failed to park the car');
        }
      });
    } else {
      $('#message').removeClass('d-none').text('Registration number invalid');
    }
  });

  // Function to add a parked car to the table
  function addCarToTable(car) {
    const row = `
      <tr data-id="${car.id}">
        <td>${car.licensee}</td>
        <td>${new Date(car.arrival).toLocaleString()}</td>
        <td>-</td>
        <td>Parked</td>
        <td class="text-right">-</td>
        <td class="text-right">
          <button class="btn btn-sm btn-danger leave-car">Leave</button>
        </td>
      </tr>`;
    $('#parking tbody').append(row);
  }

  // Mark a car as left
  $('#parking').on('click', '.leave-car', function() {
    const row = $(this).closest('tr');
    const carId = row.data('id');

    $.ajax({
      url: `http://localhost:8080/api/parking/leave/${carId}`,  // The correct leave API
      type: 'POST',
      success: function(response) {
        row.find('td:eq(2)').text(new Date(response.departure).toLocaleString());  // Update Departure time
        row.find('td:eq(3)').text('Left');  // Update Status to "Left"
        row.find('td:eq(4)').text(response.bill);  // Update Bill
        updatePlacesCount();  // Refresh available places count
      },
      error: function() {
        alert('Failed to mark the car as left');
      }
    });
  });

  // Fetch available places and update the badge
  function updatePlacesCount() {
    $.ajax({
      url: 'http://localhost:8080/api/parking/places',
      type: 'GET',
      success: function(response) {
        $('#placesCount').text(response.available);
      },
      error: function() {
        $('#placesCount').text('Error');
      }
    });
  }
});
