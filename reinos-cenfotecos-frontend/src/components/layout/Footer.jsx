import React from "react";
import {
  MapPin,
  Mail,
  PhoneCall,
  Facebook,
  Twitter,
  Instagram,
  Linkedin

} from "react-feather";
import { Link } from "react-router-dom";
//import { HashLink } from "react-router-hash-link";

const Footer = () => {

  return (
    <footer className="footer-area">
      <div className="container">
        <div className="row">
        
          <div className="col-lg-12 col-md-12">
            <div className="copyright-area">
              <p className="whiteSigma">Proyecto Universitario sin fines de lucro. Universidad Cenfotec 2021- Patrones</p>
            </div>
          </div>
        </div>
      </div>

    </footer>
  );
};
export default Footer;
