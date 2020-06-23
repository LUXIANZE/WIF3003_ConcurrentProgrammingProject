import React from 'react'
import makeStyles from "@material-ui/styles/makeStyles";
import Card from '@material-ui/core/Card';
import CardMedia from '@material-ui/core/CardMedia';
import CardContent from '@material-ui/core/CardContent';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Link from '@material-ui/core/Link';
import Sparta from "../resources/sparta.jpg";
import BoardView from './BoardView'

const useStyles = makeStyles((theme) => ({
  card: {
    position: "fixed",
    top: "50%",
    left: "50%",
    transform: "translate(-50%, -50%)",
    padding: theme.spacing(2),
    minWidth: "300px",
    width: "24%"
  },
  media: {
    height: 0,
    paddingTop: '60%',
    backgroundSize: '60%',
  },
  form: {
    display: "flex",
    flexDirection: "column"
  },
  loginButton: {
    padding: `${theme.spacing(1)}px ${theme.spacing(3)}px`,
    margin: `${theme.spacing(2)}px auto`
  }
}));

function UserForm(props) {
  const classes = useStyles();
  const [points, setPoints] = React.useState([])
  const [edges, setEdges] = React.useState([])
  const [state, setState] = React.useState({
    n: "",
    m: "",
    t: ""
  })
  props.stompClient.subscribe('/topic/points', function(message) {
    const data = JSON.parse(message.body)
    setPoints(data)
  })
  props.stompClient.subscribe('/topic/edge', function (message) {
    const data = JSON.parse(message.body)
    // alert(data)
    // let x = edges.concat(data)
    setEdges(data)
    setSubmitted(true)
  });
  const [submitted, setSubmitted] = React.useState(false)
  const handleSubmit = (e) => {
    e.preventDefault()
    props.stompClient.send("/app/user-input", {}, JSON.stringify(state));
  }

  setTimeout(()=>{},1000)
  return (
    <>
    {submitted ? <BoardView points={points} edges={edges}/> : 
      <Card className={classes.card}>
        <CardMedia
          className={classes.media}
          image={Sparta}
          title="Logo"
        />
        <CardContent>
          <form
            className={classes.form}
            onSubmit={handleSubmit}
          >
            <TextField
              id="n"
              name="n"
              label="Number of random points, n"
              required
              type="number"
              value={state.n}
              onChange={(e) => { 
                setState({ ...state, n: e.target.value }) }}
              margin="normal"
            />
            <TextField
              id="m"
              name="m"
              label="Game time, m (in seconds)"
              required
              type="number"
              value={state.m}
              onChange={(e) => { 
                setState({ ...state, m: e.target.value }) }}
              margin="normal"
            />
            <TextField
              id="t"
              name="t"
              label="Number of threads, t"
              required
              type="number"
              value={state.t}
              onChange={(e) => { 
                setState({ ...state, t: e.target.value }) }}
              margin="normal"
            />
            <Button
              variant="contained"
              color="primary"
              className={classes.loginButton}
              type="submit"
            >
              Conquer
            </Button>
          </form>
        </CardContent>
      </Card>
    }
    </>
  )
}

export default UserForm
