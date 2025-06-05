const mobileMenuToggle = document.querySelector(".header__nav-bar-icon");
const categoriesMobileContainer = document.querySelector(".header__nav-bar table tbody");
const headerContainer = document.querySelector('.header');
const mobileMenuIcon = document.querySelector('.header__nav-bar-icon');



function disableScroll() {
	document.body.style.overflow = 'hidden';
}

function enableScroll() {
	document.body.style.overflow = '';
}


//Main menu of the web,open/close managment:

mobileMenuToggle.addEventListener('click', () => {
	//To show the main menu	
	if (!categoriesMobileContainer.classList.contains("header-list-navigation--show-mobile-menu")) {		
			categoriesMobileContainer.classList.add("header-list-navigation--show-mobile-menu");
			mobileMenuIcon.classList.remove("fa-bars");
			mobileMenuIcon.classList.add("fa-xmark");
			disableScroll();			

	}
	//To close the main menu	
	else {
		categoriesMobileContainer.classList.remove("header-list-navigation--show-mobile-menu");
		mobileMenuIcon.classList.remove("fa-xmark");
		mobileMenuIcon.classList.add("fa-bars");
		enableScroll();
	}
});


//To close the main menu, when the user select one option	
	document.querySelector("#categorieLink").addEventListener('click', () => {
    if (window.matchMedia("(max-width: 1250px)").matches) {
			categoriesMobileContainer.classList.remove("header-list-navigation--show-mobile-menu");
	        mobileMenuIcon.classList.remove("fa-xmark");
	        mobileMenuIcon.classList.add("fa-bars");
	        enableScroll();			
	    }
	});



//Adjust to fix media bugs with the menus
window.addEventListener('resize', () => {
	if (window.innerWidth > 1250) {
		categoriesMobileContainer.classList.remove("header-list-navigation--show-mobile-menu");
		mobileMenuIcon.classList.remove("fa-xmark");
		mobileMenuIcon.classList.add("fa-bars");
		enableScroll();
	}
});



