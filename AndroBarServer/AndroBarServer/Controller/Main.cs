using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using AndroBarServer.Model;
using System.IO;

namespace AndroBarServer.Controller
{
    public class Main
    {
        private androbarEntities _db;

        private const string ORDERSTATUS_RECEIVED = "RECEIVED";
        private const string ORDERSTATUS_PENDING = "PENDING";

        public Main()
        {
            _db = new androbarEntities();
        }

        public IQueryable<table> GetTables()
        {
            return from tables in _db.tables
                   select tables;
        }

        public order GetTableLastOrder(int tableId)
        {
            return (from orders in _db.orders
                   where orders.TableId == tableId && orders.Status == ORDERSTATUS_RECEIVED
                   select orders).First();
        }

        public IQueryable<dynamic> GetOrderProds(order ord)
        {
            return (from op in _db.order_products
                    where op.OrderId == ord.Id
                    select op);
        }
    }
}
