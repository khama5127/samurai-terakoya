const stripe = Stripe('pk_test_51O7wztEFrMDcIzWMgQ7W38xLoVigGhgOAbw0dtXVAqSC7oVycLRROw7q8kyuEPpyFhXs2Rv7oNq7iW6RY2MxmCh900kEnsl4lJ');
const paymentButton = document.querySelector('#paymentButton');

paymentButton.addEventListener('click', () => {
    stripe.redirectToCheckout({
        sessionId: sessionId
    })
});