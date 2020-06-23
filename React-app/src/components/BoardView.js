import React from 'react'
import makeStyles from "@material-ui/styles/makeStyles";
import Card from '@material-ui/core/Card';
import CardMedia from '@material-ui/core/CardMedia';
import CardContent from '@material-ui/core/CardContent';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Link from '@material-ui/core/Link';
import Sparta from "../resources/sparta.jpg";


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
const mystyle = {
  stroke:'rgb(255,0,0)',
  strokeWidth:2,
  backgroundColor:"transparent"
}
const svgstyle = {
  backgroundColor:"transparent",
  position:"absolute",
  top: 50,
  left: 50
}
function BoardView(props) {
  var pt = props.points
  var eg = props.edges

  console.log(pt)
  console.log(eg)
  var circles = pt.map(point=>{
    return <circle cx={point.x} cy={point.y} fill="black" r="5"></circle>
  })

  var lines = eg.map(edge=>{
    console.log(edge.firstPoint.x)
    return <line x1={edge.firstPoint.x} y1={edge.firstPoint.y} x2={edge.secondPoint.x} y2={edge.secondPoint.y}  style={mystyle} ></line>
  })
  return (
    <div>
      <svg height="1000px" width="1000px" style={svgstyle}>
        {
          circles
        }
      </svg>
      <svg height="1000px" width="1000px" style={svgstyle}>
        {
          lines
        }
      </svg>
    </div>
    );
}

export default BoardView
