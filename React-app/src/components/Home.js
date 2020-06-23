import React from 'react';
import Typography from '@material-ui/core/Typography';
import Button from '@material-ui/core/Button';
import SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs'
import UserForm from './UserForm';

const style = {
  root: {
    backgroundColor: "white",
    minHeight: "100vh"
  },
  startButton: {
    position: "fixed",
    top: "50%",
    left: "50%",
    transform: "translate(-50%, -50%)",
  },
  infoBox: {
    color: "black"
  }
}
let stompClient = null;

function Home() {
  const [status, setStatus] = React.useState({
    connected: false,
    errorMessage: ""
  })
  const [started, setStarted] = React.useState({
    started: false
  })
  // const [board, setBoard] = React.useState({
  //   points: null,
  // })
  // const [points, setPoints] = React.useState([])
  const [edge, setEdge] = React.useState(null)
  const connectCallback = (frame) => {
    setStatus({
      connected: true,
      errorMessage: ""
    })
    // console.log('Connected: ' + frame);
    // stompClient.subscribe('/topic/board', function (message) {
    //   const data = JSON.parse(message.body)
    //   setBoard({
    //     points: data.points
    //   })
    // });
    // stompClient.subscribe('/topic/points', function(message) {
    //   const data = JSON.parse(message.body)
    //   setPoints(data)
    // })
    // stompClient.subscribe('/topic/edge', function (message) {
    //   const data = JSON.parse(message.body)
    //   setEdge(data.points)
    // });
  }
  const errorCallback = (error) => {
    setStatus({
      connected: false,
      errorMessage: "Please check your backend"
    })
  }
  const handleConnect = (formData) => {
    setStatus({
      connected: false,
      errorMessage: ""
    });
    const socket = new SockJS('http://localhost:8080/conquer-nodes-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, connectCallback, errorCallback);
  }
  const handleDisConnect = () => {
    if (stompClient !== null) {
      setStatus({
        connected: false,
        errorMessage: ""
      })
      stompClient.disconnect();
    }
  }
  return (
    <div className="App" style={style.root}>
      <Typography variant="h6" color="secondary">
        You are {status.connected ? "connected" : "not connected"}
      </Typography>
      {
        status.errorMessage &&
        <Typography variant="h6" color="secondary">
          {status.errorMessage}
        </Typography>
      }
      {
        status.connected
          ? <UserForm stompClient={stompClient} started={started}/>
          : <Button
            variant="contained"
            color="primary"
            onClick={handleConnect}
            className={style.startButton}
          >
            start game
            </Button>
      }
      <div style={style.infoBox}>
        {/* <div>
          topic/board:
          {board.points && board.points.map((point, i) =>
            <div key={i}>{`x: ${point.x}, y: ${point.y}`}</div>
          )}
        </div> */}
        <div>
          topic/edge:
          {edge && edge.map((point, i) =>
            <div key={i}>{`x: ${point.x}, y: ${point.y}`}</div>
          )}
        </div>
      </div>
    </div>
  );
}

export default Home;
