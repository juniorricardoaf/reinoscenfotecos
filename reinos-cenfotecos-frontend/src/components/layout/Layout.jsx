import React from 'react';
import NavMenu from "./NavMenu";
import Footer from "./Footer";

const Layout = ({ children }) => {


    return (
      <div >
      <NavMenu  />
      <div className="contenedor-min">{children}</div>
    </div>
  );
};

export default Layout;