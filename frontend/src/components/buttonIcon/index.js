function ButtonIcon({ icon, onClick, alt, className, children }) {
    return(
        <>
            <button className={`btn-icon ${className || ''}`} onClick={onClick} alt={alt}>
                <i className={icon}></i>
                {children}
            </button>
        </>
    )
}
export default ButtonIcon;