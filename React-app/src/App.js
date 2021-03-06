import React from 'react';
import Typography from '@material-ui/core/Typography';
import Button from '@material-ui/core/Button';
import SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs'
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";

import UserForm from './components/UserForm';
import Home from './components/Home';
import BoardView from './components/BoardView';

const linkStyle = {
  textDecoration:"none"
}
const buttonStyle = {
  margin:"10"
}

function App() {
  
  return (
    <Router>
      <div>
        <nav style={{padding:"10px"}}>
          <Button variant="contained" style={buttonStyle} onClick={()=>{window.location.assign("http://localhost:3000/")}}>
            Restart Game
          </Button>
        </nav>

        {/* A <Switch> looks through its children <Route>s and
            renders the first one that matches the current URL. */}
        <Switch>
          <Route path="/BoardView">
          </Route>
          <Route path="/UserForm">
          </Route>
          <Route path="/">
            <Home />
          </Route>
        </Switch>
      </div>
    </Router>
  );
}

export default App;
