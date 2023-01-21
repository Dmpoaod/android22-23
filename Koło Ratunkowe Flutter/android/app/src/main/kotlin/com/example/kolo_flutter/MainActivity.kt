import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';

void main() {
    runApp(MyApp());
}

class MyApp extends StatelessWidget {
    @override
    Widget build(BuildContext context) {
        return MaterialApp(
            title: 'Product List',
        home: ProductListPage(),
        );
    }
}

class ProductListPage extends StatefulWidget {
    @override
    _ProductListPageState createState() => _ProductListPageState();
}

class _ProductListPageState extends State<ProductListPage> {
    List<dynamic> _categories = [];
    List<dynamic> _products = [];

    @override
    void initState() {
        super.initState();
        _fetchCategories();
        _fetchProducts();
    }

    _fetchCategories() async {
        final response = await http.get('https://your-server.com/categories');
        if (response.statusCode == 200) {
            setState(() {
                _categories = json.decode(response.body);
            });
        } else {
            throw Exception('Failed to load categories');
        }
    }

    _fetchProducts() async {
        final response = await http.get('https://your-server.com/products');
        if (response.statusCode == 200) {
            setState(() {
                _products = json.decode(response.body);
            });
        } else {
            throw Exception('Failed to load products');
        }
    }

    @override
    Widget build(BuildContext context) {
        return Scaffold(
            appBar: AppBar(
                    title: Text('Product List'),
        ),
        body: Column(
        children: <Widget>[
        Expanded(
            child: ListView.builder(
                itemCount: _categories.length,
        itemBuilder: (BuildContext context, int index) {
        return ListTile(
            title: Text(_categories[index]['name']),
        );
    },
        ),
        ),
        Expanded(
            child: ListView.builder(
                itemCount: _products.length,
        itemBuilder: (BuildContext context, int index) {
        return ListTile(
            title: Text(_products[index]['name']),
        );
    },
        ),
        ),
        ],
        ),
        );
    }
}
