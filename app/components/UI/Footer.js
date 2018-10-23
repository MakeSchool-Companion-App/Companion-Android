import React from 'react';
import { StyleSheet, Text } from 'react-native';

const footer = ({ text }) => {
  return <Text style={styles.footer}>{text}</Text>;
};

const styles = StyleSheet.create({
  footer: {
    fontWeight: "bold",
    color: "white",
    fontSize: 40,
    backgroundColor: "#0D4062",
    textAlign: 'center',
    paddingTop: 30,
    paddingBottom: 30,
    paddingLeft: 30,
    paddingRight: 30,
    position: "absolute",
    left: 0,
    right: 0,
    bottom: 0
  }
});

export default footer;
