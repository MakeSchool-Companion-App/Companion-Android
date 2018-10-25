import React from 'react';
import { View, Text } from 'react-native';

import Header from '../components/UI/Header';
import Footer from '../components/UI/Footer'
import Button from '../components/UI/Button';

const home = props => {
  return (
    <View style={{flex: 1}}>
      <Header text="Make School" />
      <Button
        text="Connect to Beacon"
        onPress={() => props.history.push('/connect')}
        color="#e98ab4"
      />
      <Footer text="View Full Attendance" />
    </View>
  );
};

export default home;
