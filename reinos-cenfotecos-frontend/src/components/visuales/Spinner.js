import React, { useState } from 'react';
import "../../assets/css/Spinner.css"
import SpinnerIcon from './SpinnerIcon';


const Spinner = prop => {
    const [visible, setVisible] = useState(false);

    const showSpinner = () => setVisible(true);
    const hideSpinner = () => setVisible(false);
    const spinner = visible ? <SpinnerIcon /> : null;

    return [ spinner, showSpinner, hideSpinner ]
}
export default Spinner;