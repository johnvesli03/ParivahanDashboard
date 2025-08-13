/**
 * main.js
 * This script handles the client-side interactions for the Vahan Dashboard.
 */
document.addEventListener('DOMContentLoaded', function() {
    // Get the form and the loader overlay element
    const filterForm = document.querySelector('.filter-form');
    const loaderOverlay = document.getElementById('loader-overlay');

    // Check if the form and loader exist on the page
    if (filterForm && loaderOverlay) {
        // Add a submit event listener to the form
        filterForm.addEventListener('submit', function() {
            // When the form is submitted (e.g., by changing a dropdown),
            // make the loader overlay visible.
            loaderOverlay.classList.add('visible');
        });
    }

    // Note: The loader will be hidden automatically on the next page load.
    // For a single-page application (SPA), you would hide it manually
    // after the new data has been loaded via AJAX.
});
