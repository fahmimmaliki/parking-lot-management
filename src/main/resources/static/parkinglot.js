$(document).ready(function() {
    const parkingData = [];
    const maxPlaces = 10;
    let availablePlaces = maxPlaces;
  
    // Update places count
    function updatePlacesCount() {
      $("#placesCount").text(availablePlaces);
    }
  
    // Add car
    $("#carButton").click(function() {
      const registrationNumber = $("#carValue").val();
      const arrivalTime = new Date().toLocaleString();
  
      if (!registrationNumber) {
        $("#message").removeClass('d-none');
        return;
      }
  
      if (availablePlaces > 0) {
        const car = {
          registrationNumber,
          arrival: arrivalTime,
          departure: "-",
          status: "Parked",
          bill: "-",
          actions: `<button class="btn btn-sm btn-danger leaveButton">Leave</button>`
        };
  
        parkingData.push(car);
        availablePlaces--;
        $("#message").addClass('d-none');
        renderTable();
      } else {
        alert("No available places!");
      }
  
      updatePlacesCount();
      $("#carValue").val('');
    });
  
    // Render parking table
    function renderTable() {
      const tbody = $("#parking tbody");
      tbody.empty();
  
      parkingData.forEach((car, index) => {
        const row = `<tr>
          <td>${car.registrationNumber}</td>
          <td>${car.arrival}</td>
          <td>${car.departure}</td>
          <td>${car.status}</td>
          <td class="text-right">${car.bill}</td>
          <td class="text-right">${car.actions}</td>
        </tr>`;
        tbody.append(row);
      });
  
      // Add event listener to 'Leave' buttons
      $(".leaveButton").click(function() {
        const rowIndex = $(this).closest('tr').index();
        handleLeave(rowIndex);
      });
    }
  
    // Handle car leaving
    function handleLeave(index) {
      const departureTime = new Date().toLocaleString();
      const car = parkingData[index];
      car.departure = departureTime;
      car.status = "Left";
      car.bill = "$10";  // Calculate bill based on time, for simplicity it's $10
      car.actions = "-";
      availablePlaces++;
      updatePlacesCount();
      renderTable();
  
      // Show modal with summary
      const summary = `
        <p><strong>Registration No:</strong> ${car.registrationNumber}</p>
        <p><strong>Arrival:</strong> ${car.arrival}</p>
        <p><strong>Departure:</strong> ${car.departure}</p>
        <p><strong>Bill:</strong> ${car.bill}</p>
      `;
      $("#modalBody").html(summary);
      $("#myModal").modal('show');
    }
  
    // Initial update
    updatePlacesCount();
  });
  