import Home from "../../features/home/";
import Login from "../../features/auth/login/";
import Cadastro from "../../features/auth/cadastro/";
import Cursos from "../../features/cursos";
import CursoPage from "../../features/cursos/cursoPage";
import NotFound from "../../features/not-found/";

export const publicRoutes = [
  {
    path: "/",
    element: <Home />
  },
  {
    path: "/entrar",
    element: <Login />
  },
  {
    path: "/cadastro",
    element: <Cadastro />
  },
  {
    path: "/cursos",
    element: <Cursos />
  },
  {
    path: "/cursos/:slug",
    element: <CursoPage />
  },
  {
    path: "*",
    element: <NotFound />
  }
];