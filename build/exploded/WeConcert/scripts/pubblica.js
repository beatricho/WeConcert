document.addEventListener('DOMContentLoaded', () => {
    const etaMinInput = document.getElementById('etaMin');
    const etaMaxInput = document.getElementById('etaMax');

    etaMaxInput.addEventListener('input', () => {
        const etaMinValue = parseInt(etaMinInput.value);
        const etaMaxValue = parseInt(etaMaxInput.value);

        if (etaMaxValue < etaMinValue) {
            etaMaxInput.setCustomValidity('L\'età massima non può essere inferiore all\'età minima.');
        } else {
            etaMaxInput.setCustomValidity('');
        }
    });
});
