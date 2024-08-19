const Footer = () => {
  return (
    <footer className="bg-gray-800 text-white py-4 mt-auto">
      <div className="container mx-auto text-center">
        <p>&copy; 2024 ChronoCraft. All rights reserved.</p>
        <p>
          <a href="/about" className="hover:underline">
            About Us
          </a>{" "}
          |{" "}
          <a href="/contact" className="hover:underline">
            Contact
          </a>{" "}
          |{" "}
          <a href="/privacy" className="hover:underline">
            Privacy Policy
          </a>
        </p>
      </div>
    </footer>
  );
};

export default Footer;
