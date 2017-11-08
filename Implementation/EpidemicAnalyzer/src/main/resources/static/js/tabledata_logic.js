//Highlight patient case table's rows based on current infection status
$(function () {
    $("tr").each(function () {
        var col_val = $(this).find("td:eq(1)").text();
        if (col_val.toUpperCase() === "INFECTED") {
            $(this).addClass('InfectedMark');
        } else if (col_val.toUpperCase() === "SUSCEPTIBLE") {
            $(this).addClass('SusceptibleMark');
        } else if (col_val.toUpperCase() === "RECOVERED") {
            $(this).addClass('RecoveredMark');
        }

    });
    $('#patientcasetable').DataTable({
        "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
        "autoWidth": false,
        language: {
            search: "_INPUT_",
            searchPlaceholder: "Search..."
        }
    });

});
