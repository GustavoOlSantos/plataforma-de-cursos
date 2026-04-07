import ProtectedRoute from "./ProtectedRoute";
import Perfil from "../../features/perfil/";

export const protectedRoutes = [
  {
    path: "/perfil",
    element: (
      <ProtectedRoute>
        <Perfil />
      </ProtectedRoute>
    )
  }
];