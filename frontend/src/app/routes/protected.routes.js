import ProtectedRoute from "./ProtectedRoute";
import Perfil from "../../features/perfil/";
import MeusCursos from "../../features/cursos/meusCursos/";
import VerCurso from "../../features/cursos/verCursoPage/";
import Certificado from "../../features/cursos/certificadoPage/";

export const protectedRoutes = [
  {
    path: "/perfil",
    element: (
      <ProtectedRoute>
        <Perfil />
      </ProtectedRoute>
    ),
  },
  {
    path: "/ver-curso/:slug",
    element: (
      <ProtectedRoute>
        <VerCurso />
      </ProtectedRoute>
    ),
  },
  { 
    path: "/cursos/:slug/certificado",
    element: (
      <ProtectedRoute>
        <Certificado />
      </ProtectedRoute>
    )
  },
  { 
    path: "/meus-cursos",
    element: (
      <ProtectedRoute>
        <MeusCursos />
      </ProtectedRoute>
    )
  }
];