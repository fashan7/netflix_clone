import Navbar from "../../components/navbar/Navbar";
import Featured from "../../components/featured/Featured";
import "./home.scss";
import List from "../../components/list/List";

const Home = () => {
  
  
  return (
    <div className="home">
      <Navbar />
      <Featured/>
      <List title='Adventure Movies' movieCat="Adventure"/>
      <List title='Action Movies' movieCat="Action"/>
      <List title='Comedy Movies' movieCat="Comedy"/>
      <List title='Animations' movieCat="Animation"/>
    </div>
  );
};

export default Home;
