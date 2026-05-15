import { useEffect } from "react";
import { useNavigate } from "react-router-dom";

export function AuthListener() {
  const navigate = useNavigate();

  useEffect(() => {
    function handleManualLogout() {
      navigate("/", { replace: true });
    }

    function handleSessionExpired() {
      navigate("/entrar", { replace: true });
    }

    window.addEventListener("manual-logout", handleManualLogout);

    window.addEventListener("session-expired", handleSessionExpired);

    return () => {
      window.removeEventListener(
        "manual-logout",
        handleManualLogout
      );

      window.removeEventListener(
        "session-expired",
        handleSessionExpired
      );
    };
  }, [navigate]);

  return null;
}