import react from "react";

function SocialBtn({icon, alt, tooltip, href}) {
    return(
        <>
            <a className="social-btn" href={href} alt={alt} tooltip={tooltip} target="_blank">
                <i className={icon}></i>
            </a>
        </>
    )
}

export default SocialBtn